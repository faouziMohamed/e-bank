import * as Plot from 'https://cdn.skypack.dev/@observablehq/plot@0.1';

'use strict';

export function getPlot(props) {
  let { data, width, height } = props;
  let plot = Plot.plot({
    width: `${ width }`,
    height: `${ height }`,
    marginLeft: 120,
    marginTop: 10,
    marginRight: 100,
    marginBottom: 60,
    x: {
      grid: true,
      label: 'Balance →'
    },
    y: {
      grid: true,
      label: 'Account Name →',
    },
    marks: [
      Plot.barX(data, {
        y: 'wording',
        x: 'balance',
        stroke: '#222200',
        fill: '#00015b',
      }),
      Plot.dot(data, {
        y: 'wording',
        x: 'balance',
        fill: '#119955',
        stroke: '#008833'
      }),
      Plot.text(data, {
        x: 'balance', y: 'wording',
        text: (d) => new Intl.NumberFormat().format(d.balance),
        color: 'red',
        dx: 45
      })
      , Plot.ruleY([ 0 ])
      , Plot.ruleX([ 0 ]),
    ],
  });
  plot.classList.add('chart');
  return plot;
}

export function drawUserData(props) {
  let { data, className, elClassNameToRemove } = props;

  // sva: Saving Account
  // cra: Current Account
  let { sva, cra } = data;

  let plotSva = getPlot({
    data: sva,
    width: 600,
    height: sva.length > 10 ? 600 : 400
  });

  let plotCra = getPlot({
    data: cra,
    width: 600,
    height: cra.length > 10 ? 600 : 400
  });

  plotSva.classList.add('sva-chart');
  plotCra.classList.add('cra-chart');

  let child = document.querySelector(`.${ elClassNameToRemove }`);
  let plotContainer = document.querySelector(`.${ className }`);
  plotContainer.removeChild(child);
  plotContainer.appendChild(plotSva);
  plotContainer.appendChild(plotCra);
}
