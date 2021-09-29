// eslint-disable-next-line import/no-unresolved
import * as Plot from 'https://cdn.skypack.dev/@observablehq/plot@0.1';

export function getPlot(props) {
  const { data, width, height } = props;
  const plot = Plot.plot({
    width,
    height,
    marginLeft: 120,
    marginTop: 10,
    marginRight: 100,
    marginBottom: 60,
    x: { grid: true, label: 'Balance →' },
    y: { grid: true, label: 'Account Name →' },
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
        stroke: '#008833',
      }),
      Plot.text(data, {
        x: 'balance',
        y: 'wording',
        text: (d) => new Intl.NumberFormat().format(d.balance),
        color: 'red',
        dx: 45,
      }),
      Plot.ruleY([0]),
      Plot.ruleX([0]),
    ],
  });
  plot.classList.add('chart');
  return plot;
}

export function drawUserData(props) {
  const { data, className, elClassNameToRemove } = props;

  // sva: Saving Account
  // cra: Current Account
  const { sva, cra } = data;

  const plotSva = getPlot({
    data: sva,
    width: 600,
    height: sva.length > 10 ? 600 : 400,
  });

  const plotCra = getPlot({
    data: cra,
    width: 600,
    height: cra.length > 10 ? 600 : 400,
  });

  plotSva.classList.add('sva-chart');
  plotCra.classList.add('cra-chart');

  const child = document.querySelector(`.${elClassNameToRemove}`);
  const plotContainer = document.querySelector(`.${className}`);
  plotContainer.removeChild(child);
  plotContainer.appendChild(plotSva);
  plotContainer.appendChild(plotCra);
}
